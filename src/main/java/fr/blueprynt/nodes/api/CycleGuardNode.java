package fr.blueprynt.nodes.api;

/**
 * Marker interface: this node introduces a delay or trigger limit that protects
 * a blueprint against infinite execution cycles.
 * <p>
 * The alert engine recognises any node implementing this interface as a cycle
 * guard — no hardcoded ID list required.
 */
public interface CycleGuardNode {}
