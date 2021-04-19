public class TestEnum {
    enum Color {
        RED, GREEN, BLUE;
    }

    enum Shape {
        CIRCLE, TRIANGLE, RECTANGLE;
        Shape() {
            System.out.println("At constructor of " + this.toString());
        }
        int getNumberOfVertices() {
            switch (this) {
                case CIRCLE: {
                    return 0;
                }
                case TRIANGLE: {
                    return 3;
                }
                case RECTANGLE: {
                    return 4;
                }
                default: {
                    return -1;
                }
            }
        }
    }

    enum Sense {
        TOUCH("Skin"), SIGHT("Eyes"), HEARING("Ears"), SMELL("Nose"), TASTE("Tongue");

        private String sensoryOrgan;

        private Sense(String sensingOrgan) {
            this.sensoryOrgan = sensingOrgan;
        }

        public String getSensoryOrgan() {
            return this.sensoryOrgan;
        }
    }

    public static void main(String[] args) {
        { // Test basic enum
            Color color = Color.BLUE;
            System.out.println(color);
        }
        { // Test values(), ordinal(), valueOf()
            Color[] colors = Color.values();
            for (Color color : colors) {
                System.out.println("Color " + color + " is at index: " + color.ordinal());
            }
            System.out.println(Color.valueOf("BLUE"));
        }
        { // Test enum constructor and enum methods
            Shape shape = Shape.TRIANGLE;
            System.out.println(shape + " has " + shape.getNumberOfVertices() + " vertices");
        }
        { // Test enum with customized value
            Sense sense = Sense.TOUCH;
            System.out.println(sense.getSensoryOrgan() + " is the sensory organ of " + sense);
        }
    }
}