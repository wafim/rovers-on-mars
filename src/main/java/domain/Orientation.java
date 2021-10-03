package domain;

public enum Orientation {
    N("NORTH"),S("SOUTH"),E("EAST"),W("WEAST");
    public final String label;

    Orientation(String label) {
        this.label = label;

    }

    public static boolean contains(String label) {

        for (Orientation orientation : Orientation.values()) {
            if (orientation.name().equals(label)) {
                return true;
            }
        }

        return false;
    }
}
