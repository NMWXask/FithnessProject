
    @FunctionalInterface
    public interface Calculator<T extends Number> {
        double calculateFees(T clubID);  //функциональный интерфейс содержит ОДИН абстактный метод
    }

