package be.acerta.pieter.advent2021.day2;

public class Command {
    private final Direction direction;
    private final long amount;

    private Command(Builder builder) {
        this.direction = builder.direction;
        this.amount = builder.amount;
    }

    public Direction getDirection() {
        return direction;
    }

    public long getAmount() {
        return amount;
    }

    public long getImpactOnXAxis() {
        switch (direction) {
            case FORWARD:
                return amount;
            default:
                return 0;
        }
    }

    public long getImpactOnZAxis() {
        if (direction == Direction.UP)
            return amount;
        if (direction == Direction.DOWN)
            return -1l * amount;
        else
            return 0;
    }

    public static Builder newCommand() {
        return new Builder();
    }

    public static final class Builder {
        private Direction direction;
        private long amount;

        private Builder() {
        }

        public static Builder aCommand() {
            return new Builder();
        }

        public Builder withDirection(Direction direction) {
            this.direction = direction;
            return this;
        }

        public Builder withAmount(long amount) {
            this.amount = amount;
            return this;
        }

        public Command build() {
            return new Command(this);
        }
    }

    public static Command fromString(String commandAsString) {
        String[] parts = commandAsString.split(" ");
        return newCommand()
                .withDirection(Direction.fromString(parts[0]))
                .withAmount(Long.parseLong(parts[1]))
                .build();
    }
}
