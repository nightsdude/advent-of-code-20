public class Position {
    public enum Command {
        E,
        S,
        W,
        N,
        F,
        R,
        L
    }

    private enum Orientation {
        EAST(0),
        SOUTH(1),
        WEST(2),
        NORTH(3);
        
        private final int value;
        private Orientation(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public static Orientation fromInt(int x) {
            switch(x) {
            case 0:
                return EAST;
            case 1:
                return SOUTH;
            case 2:
                return WEST;
            case 3:
                return NORTH;
            default:
                return null;
            }
        }
    }

    private Orientation orientation;
    private int x;
    private int y;

    Position() {
        this.orientation = Orientation.EAST;
        this.x = 0;
        this.y = 0;
    }

    public Orientation getOrientation() {
        return this.orientation;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    public void executeCommand(Command command, int value) {
        switch(command) {
            case E:
                this.x += value;
                break;
            case S:
                this.y -= value;
                break;
            case W:
                this.x -= value;
                break;
            case N:
                this.y += value;
                break;
            case F:
                switch (this.orientation) {
                    case EAST:
                        this.x += value;
                        break;
                    case SOUTH:
                        this.y -= value;
                        break;
                    case WEST:
                        this.x -= value;
                        break;
                    case NORTH:
                        this.y += value;
                        break;
                    default:
                        break;
                }
                break;
            case R:
                switch(value) {
                    case 90:
                        this.orientation = Orientation.fromInt((this.orientation.getValue() + 1) % 4);
                        break;
                    case 180:
                        this.orientation = Orientation.fromInt((this.orientation.getValue() + 2) % 4);
                        break;
                    case 270:
                        this.orientation = Orientation.fromInt((this.orientation.getValue() + 3) % 4);
                        break;
                    default:
                        break;
                }
                break;
            case L:
                switch(value) {
                    case 90:
                        this.orientation = Orientation.fromInt((this.orientation.getValue() + 3) % 4);
                        break;
                    case 180:
                        this.orientation = Orientation.fromInt((this.orientation.getValue() + 2) % 4);
                        break;
                    case 270:
                        this.orientation = Orientation.fromInt((this.orientation.getValue() + 1) % 4);
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }
    }   

    public int getManhattanDistance() {
        return Math.abs(this.x) + Math.abs(this.y);
    }
}