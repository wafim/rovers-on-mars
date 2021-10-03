package domain;

public enum Rotation {
    L("LEFT"),R("RIGHT");
    public final String label;

    Rotation(String label) {
        this.label = label;
    }

    public static boolean contains(String label) {

        for (Rotation rotation : Rotation.values()) {
            if (rotation.name().equals(label)) {
                return true;
            }
        }

        return false;
    }
}
