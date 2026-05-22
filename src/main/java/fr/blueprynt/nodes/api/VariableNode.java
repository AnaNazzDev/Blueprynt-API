package fr.blueprynt.nodes.api;

/**
 * Marker interface: this node reads or writes variables via the VariableEngine.
 * Used by the alert engine to apply VAR-* validation rules without maintaining
 * a hardcoded list of node IDs.
 */
public interface VariableNode {}
