package fr.blueprynt.nodes.api;

public interface ScheduledTriggerNode extends InstantTriggerableNode, TriggerNode {
    long getIntervalTicks(NodeData config);
}
