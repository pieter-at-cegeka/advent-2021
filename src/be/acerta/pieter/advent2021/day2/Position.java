package be.acerta.pieter.advent2021.day2;

public class Position {
    private long positionAlongXAxis, depth, currentAim;

    public Position() {
        positionAlongXAxis = depth = currentAim= 0l;
    }

    public long getPositionAlongXAxis() {
        return positionAlongXAxis;
    }

    public long getDepth() {
        return depth;
    }

    public long getCurrentAim() {
        return currentAim;
    }

    public void apply(Command command) {
        long amount = command.getAmount();
        switch (command.getDirection()) {
            case FORWARD:
                positionAlongXAxis += amount;
                depth += currentAim * amount;
                break;
            case DOWN:
                currentAim += amount;
                break;
            case UP:
                currentAim -= amount;
                break;
            default: throw new UnsupportedOperationException("Direction " + command.getDirection() + " is not supported");
        }
    }
}
