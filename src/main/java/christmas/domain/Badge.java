package christmas.domain;

public enum Badge {
    SANTA("산타", 20000),
    TREE("트리", 10000),
    STAR("별", 5000),
    NONE("없음", 0);

    private final String name;
    private final int condition;

    Badge(String name, int condition) {
        this.name = name;
        this.condition = condition;
    }

    public static Badge findBadge(int totalDiscount) {
        for (Badge badge : Badge.values()) {
            if (totalDiscount >= badge.getCondition()) {
                return badge;
            }
        }
        return NONE;
    }

    public String getName() {
        return name;
    }

    public int getCondition() {
        return condition;
    }
}
