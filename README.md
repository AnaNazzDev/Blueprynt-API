# Blueprynt-API

[![](https://jitpack.io/v/AnaNazzDev/Blueprynt-API.svg)](https://jitpack.io/#AnaNazzDev/Blueprynt-API)

Public Java API for [**Blueprynt**](https://builtbybit.com/resources/blueprynt-visual-scripting-engine.108801/) — the visual node-based scripting engine for Paper servers.

Use this dependency to register custom nodes, listen to blueprint executions, fire your own triggers, and declare custom variable types from your plugin.

📖 **Full developer documentation:** [wiki.multiverse-studio.fr/dev-api](https://wiki.multiverse-studio.fr/dev-api)

> The classes in this repository are **stubs**. They throw `UnsupportedOperationException` at runtime unless the Blueprynt plugin is installed on the server — Blueprynt's classloader injects the real implementations at startup.

---

## Installation

### Gradle (Kotlin DSL)

```kotlin
repositories {
    maven("https://jitpack.io")
}

dependencies {
    compileOnly("com.github.AnaNazzDev:Blueprynt-API:VERSION")
}
```

### Gradle (Groovy)

```groovy
repositories {
    maven { url 'https://jitpack.io' }
}

dependencies {
    compileOnly 'com.github.AnaNazzDev:Blueprynt-API:VERSION'
}
```

### Maven

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>com.github.AnaNazzDev</groupId>
        <artifactId>Blueprynt-API</artifactId>
        <version>VERSION</version>
        <scope>provided</scope>
    </dependency>
</dependencies>
```

Replace `VERSION` with the latest tag from the [Releases page](https://github.com/AnaNazzDev/Blueprynt-API/releases)

### plugin.yml

```yaml
name: MyPlugin
version: 1.0.0
main: com.example.MyPlugin
api-version: '1.21'
depend: [Blueprynt]      # or softdepend: [Blueprynt] if the integration is optional
```

---

## Quick start

A minimal action node that messages a player:

```java
import fr.blueprynt.engine.ExecutionContext;
import fr.blueprynt.model.VarType;
import fr.blueprynt.nodes.api.*;
import org.bukkit.entity.Player;
import java.util.List;

@BlueprintNode(
        id = "myplugin:say_hello",
        label = "Say Hello",
        category = "Chat",
        nodeType = NodeType.ACTION
)
public class SayHelloNode implements ActionNode {

    @Override
    public List<InputHandle> getInputHandles() {
        return List.of(
                new InputHandle("exec_in", VarType.EXEC, ""),
                new InputHandle("player", VarType.PLAYER, "Player")
        );
    }

    @Override
    public List<OutputHandle> getOutputHandles() {
        return List.of(new OutputHandle("exec_out", VarType.EXEC, ""));
    }

    @Override
    public boolean requiresMainThread() { return true; }

    @Override
    public void execute(ExecutionContext ctx, NodeData config) {
        Player player = ctx.resolve("player", config, Player.class);
        if (player != null) {
            player.sendMessage("Hello from Blueprynt!");
        }
    }
}
```

Register it from your plugin's `onEnable()`:

```java
import fr.blueprynt.api.BluepryntApi;
import org.bukkit.plugin.java.JavaPlugin;

public final class MyPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        if (getServer().getPluginManager().getPlugin("Blueprynt") == null) {
            getLogger().warning("Blueprynt not installed — integration disabled.");
            return;
        }
        BluepryntApi.registerNode(new SayHelloNode());
    }

    @Override
    public void onDisable() {
        BluepryntApi.unregisterNode("myplugin:say_hello");
    }
}
```

That's it — the node now appears in the editor under the **Chat** category and can be wired into any blueprint.

---

## API surface

The only public entry point is [`fr.blueprynt.api.BluepryntApi`](src/main/java/fr/blueprynt/api/BluepryntApi.java):

| Method | Purpose |
|---|---|
| `registerNode(Object node)` | Register a custom node (must be `@BlueprintNode` + implement a node interface). |
| `unregisterNode(String nodeId)` | Unregister and asynchronously revalidate blueprints. |
| `revalidateBlueprints()` | Force a refresh of all stored blueprints. |
| `registerVariableType(VariableTypeDescriptor)` | Add a custom variable type from a descriptor. |
| `registerVariableType(VariableType)` | Add a custom variable type from an implementation. |
| `declareVariable(VarScope, String, String, String)` | Programmatically declare a persistent variable. |
| `fireTrigger(String, Player, Map)` | Fire a custom trigger that blueprints can listen for. |
| `onBlueprintExecuted(Consumer)` | Subscribe to blueprint executions; returns an `AutoCloseable`. |

### Node interfaces

All under `fr.blueprynt.nodes.api`:

- `TriggerNode` — listens to Bukkit events, opens execution contexts
- `ActionNode` — performs side effects, optionally on the main thread
- `ConditionNode` — returns a boolean for Then/Else branching
- `LogicNode` — multi-output control flow (loops, branches, etc.)
- `ValueNode` — pure value producers
- `VariableNode` / `VariableWriterNode` — variable read / write nodes

Every non-trigger node implements `NodeSpec` and declares its **input handles**, **output handles** and **config fields**. See [`NodeSpec`](src/main/java/fr/blueprynt/nodes/api/NodeSpec.java) and [`NodeConfigField`](src/main/java/fr/blueprynt/nodes/api/NodeConfigField.java) for the full contract.

### Handle types

Defined in `fr.blueprynt.model.VarType`: `EXEC`, `PLAYER`, `STRING`, `INT`, `FLOAT`, `BOOL`, `LIST`, `MAP`, `OBJECT`, `LOCATION`, `ENTITY`, `JSON`.

`STRING` and `OBJECT` are universal wildcards (compatible with everything). `EXEC` is the control-flow wire and is never used for data.

### Execution context

[`ExecutionContext`](src/main/java/fr/blueprynt/engine/ExecutionContext.java) is the per-execution scratchpad passed to every node. The methods you'll use most:

```java
Player p = ctx.resolve("player", config, Player.class);   // resolve an input handle
ctx.set("result", someValue);                             // expose a value on an output handle
Player trigger = ctx.triggerPlayer();                     // who triggered this blueprint?
String bpId = ctx.blueprintId();
```

---

## More examples & documentation

- 📖 **[Developer documentation](https://wiki.multiverse-studio.fr/dev-api)** — full wiki with detailed guides on nodes, variables, triggers, configuration fields, alert rules, and more.
- 🧩 **[Blueprynt-Public](https://github.com/AnaNazzDev/Blueprynt-Public)** — ready-to-read example code (custom nodes, listeners, custom triggers), issue tracker and roadmap.

---

## Versioning

Semantic versioning. `BluepryntApi` is **stable** — breaking changes only on a major version bump. Everything outside `fr.blueprynt.api` and `fr.blueprynt.nodes.api` is internal and may change without notice (in practice these are also kept stable, but no guarantees).

## Requirements

- **Minecraft:** 1.21+
- **Server:** Paper (or Paper forks)
- **Java:** 21+
- **Blueprynt plugin:** installed on the server at runtime (this dependency is `compileOnly` / `provided`)

## Links

- 🛒 [Blueprynt on BBB](https://builtbybit.com/resources/blueprynt-visual-scripting-engine.108801/)
- 📖 [Wiki — User guide](https://wiki.multiverse-studio.fr) (EN / FR)
- 🛠️ [Wiki — Developer API reference](https://wiki.multiverse-studio.fr/dev-api)
- 📦 [Blueprynt-Public](https://github.com/AnaNazzDev/Blueprynt-Public) — issue tracker, extended examples, roadmap
- 💬 [Discord](https://discord.gg/UHUJJjHEpJ)

## License

The contents of this repository (API stubs, documentation) are released under the MIT License. The Blueprynt plugin itself is proprietary, paid software distributed separately.
