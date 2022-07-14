enum Operation {
    SUM {
        public int perform(int a, int b) {
            return a + b;
        }
    },
    SUBTRACT {
        public int perform(int a, int b) {
            return a - b;
        }
    },
    MULTIPLY {
        public int perform(int a, int b) {
            return a * b;
        }
    },
    DIVISION {
        public int perform(int a, int b) {
            return a / b;
        }
    };

    public abstract int perform(int a, int b);
}
