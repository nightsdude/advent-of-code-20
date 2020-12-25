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

    private int relativeWayPointX;
    private int relativeWayPointY;
    private int shipX;
    private int shipY;

    Position() {
        this.relativeWayPointX = 10;
        this.relativeWayPointY = 1;
        this.shipX = 0;
        this.shipY = 0;
    }

    public void executeCommand(Command command, int value) {
        int newX = 0;
        int newY = 0;

        switch(command) {
            case E:
                this.relativeWayPointX += value;
                break;
            case S:
                this.relativeWayPointY -= value;
                break;
            case W:
                this.relativeWayPointX -= value;
                break;
            case N:
                this.relativeWayPointY += value;
                break;
            case F:
                this.shipX += this.relativeWayPointX * value;
                this.shipY += this.relativeWayPointY * value;
                break;
            case L:
                switch(value) {
                    case 90:
                        newX = -1 * this.relativeWayPointY;
                        newY = this.relativeWayPointX;
                        this.relativeWayPointX = newX;
                        this.relativeWayPointY = newY;
                        break;
                    case 180:
                        newX = -1 * this.relativeWayPointX;
                        newY = -1 * this.relativeWayPointY;
                        this.relativeWayPointX = newX;
                        this.relativeWayPointY = newY;
                        break;
                    case 270:
                        newX = this.relativeWayPointY;
                        newY = -1 * this.relativeWayPointX;
                        this.relativeWayPointX = newX;
                        this.relativeWayPointY = newY;
                        break;
                    default:
                        break;
                }
                break;
            case R:
                switch(value) {
                    case 90:
                        newX = this.relativeWayPointY;
                        newY = -1 * this.relativeWayPointX;
                        this.relativeWayPointX = newX;
                        this.relativeWayPointY = newY;
                        break;
                    case 180:
                        newX = -1 * this.relativeWayPointX;
                        newY = -1 * this.relativeWayPointY;
                        this.relativeWayPointX = newX;
                        this.relativeWayPointY = newY;
                        break;
                    case 270:
                        newX = -1 * this.relativeWayPointY;
                        newY = this.relativeWayPointX;
                        this.relativeWayPointX = newX;
                        this.relativeWayPointY = newY;
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
        return Math.abs(this.shipX) + Math.abs(this.shipY);
    }
}