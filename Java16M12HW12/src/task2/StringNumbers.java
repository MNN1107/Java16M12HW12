package task2;

public class StringNumbers {
    private int n;
    private int currentNumber = 1;
        public StringNumbers(int n) {
            this.n = n;
        }
        public void fizz() throws InterruptedException {
            while (currentNumber <= n) {
                synchronized (this){
                    if (currentNumber % 3 == 0 && currentNumber % 5 != 0) {
                        System.out.println("fizz");
                        currentNumber++;
                        notifyAll();
                    }
                    else {
                        wait();
                    }
            }
            }
        }

        public void buzz() throws InterruptedException {
            while (currentNumber <= n) {
                synchronized (this){
                    if (currentNumber % 5 == 0 && currentNumber % 3 != 0) {
                        System.out.println("buzz");
                        currentNumber++;
                        notifyAll();
                    }else {
                        wait();
                    }
                }

            }
        }

        public void fizzbuzz() throws InterruptedException {
            while (currentNumber <= n) {
                synchronized (this) {
                    if (currentNumber % 3 == 0 && currentNumber % 5 == 0) {
                        System.out.println("fizzbuzz");
                        currentNumber++;
                        notifyAll();
                    }else {
                        wait();
                    }
                }
            }
        }

        public void number() throws InterruptedException {
            while (currentNumber <= n) {
                synchronized (this) {
                    if (currentNumber % 3 != 0 && currentNumber % 5 != 0) {
                        System.out.println(currentNumber);
                        currentNumber++;
                        notifyAll();
                    }else {
                        wait();
                    }
                }
            }
        }

        public static void main(String[] args) {
            int n = 20;
            StringNumbers stringNumbers = new StringNumbers(n);

            Thread threadA = new Thread(() -> {
                try {
                    stringNumbers.fizz();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

            Thread threadB = new Thread(() -> {
                try {
                    stringNumbers.buzz();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

            Thread threadC = new Thread(() -> {
                try {
                    stringNumbers.fizzbuzz();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

            Thread threadD = new Thread(() -> {
                try {
                    stringNumbers.number();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

            threadA.start();
            threadB.start();
            threadC.start();
            threadD.start();

            try {
                threadA.join();
                threadB.join();
                threadC.join();
                threadD.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
}