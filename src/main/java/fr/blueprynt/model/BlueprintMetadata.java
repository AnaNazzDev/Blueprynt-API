package fr.blueprynt.model;

/** Editor viewport state stored alongside a blueprint (zoom, pan position). */
public record BlueprintMetadata(double editorPositionX, double editorPositionY, double editorZoom) {

    public static BlueprintMetadata defaults() {
        return new BlueprintMetadata(0, 0, 1);
    }
}
