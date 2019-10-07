package adp_1;

import java.util.Random;

public abstract class PerformanceTest {
    private static MyList<Integer> _myList = new MyList<>();
    private static LinkedList<Integer> _linkedList = new LinkedList<>();
    private static Random _random = new Random();

    public static void main(String[] args) {
        if (args.length != 4) {
            System.out.printf("Wrong size of arguments [%s] repExp repOp N\n", PerformanceTest._inputsToString());
            return;
        }
        String operation = args[0];
        int repExp = Integer.parseInt(args[1]);
        int repOp = Integer.parseInt(args[2]);
        int n = Integer.parseInt(args[3]);
        System.out.printf("%s %d-mal, Listenlänge=%d, Anzahl Exp=%d\n", Input.valueOf(operation), repOp, n, repExp);

        PerformanceTest._start(Input.valueOf(operation), repExp, repOp, n);
    }

    private static String _inputsToString() {
        Input[] inputs = Input.values();
        StringBuffer buffer = new StringBuffer();
        for (Input input : inputs) {
            buffer.append(input);
            buffer.append("|");
        }
        buffer.deleteCharAt(buffer.length() - 1);
        return buffer.toString();
    }

    private static void _start(Input operation, int repExp, int repOp, int n) {
        PerformanceTest._fillLists(n);
        System.out.printf("%10s\t%16s\t%10s\n", "Wdh", "Zeit(ms)", "Zeit/repOp(ms)");
        switch (operation) {
            case insert:
                PerformanceTest._testAllInsert(repExp, repOp, n);
                break;
            case contains:
                PerformanceTest._testAllContains(repExp, repOp, n);
                break;
            case delete:
                PerformanceTest._testAllDelete(repExp, repOp, n);
                break;
            case deleteRand:
                PerformanceTest._testAllDeleteRandom(repExp, repOp, n);
                break;
            default:
                throw new Error("Wrong operation \"" + operation + "\"! Use: " + PerformanceTest._inputsToString());
        }
    }

    private static void _resetLists(int n) {
        PerformanceTest._myList = new MyList<>();
        PerformanceTest._linkedList = new LinkedList<>();
        PerformanceTest._fillLists(n);
    }

    private static void _testAllContains(int repExp, int repOp, int n) {
        System.out.print("ArrayList\n");
        for (int i = 0; i < repExp; ++i) {
            PerformanceTest._resetLists(n);
            Stopwatch stopwatch = new Stopwatch();
            PerformanceTest._testContains(PerformanceTest._myList, repOp);
            PerformanceTest._printResult(stopwatch, repOp);
        }
        System.out.print("LinkedList\n");
        for (int i = 0; i < repExp; ++i) {
            PerformanceTest._resetLists(n);
            Stopwatch stopwatch = new Stopwatch();
            PerformanceTest._testContains(PerformanceTest._linkedList, repOp);
            PerformanceTest._printResult(stopwatch, repOp);
        }
    }

    private static void _testAllDelete(int repExp, int repOp, int n) {
        System.out.print("ArrayList\n");
        for (int i = 0; i < repExp; ++i) {
            PerformanceTest._resetLists(n);
            Stopwatch stopwatch = new Stopwatch();
            PerformanceTest._testDelete(PerformanceTest._myList, repOp);
            PerformanceTest._printResult(stopwatch, repOp);
        }
        System.out.print("LinkedList\n");
        for (int i = 0; i < repExp; ++i) {
            PerformanceTest._resetLists(n);
            Stopwatch stopwatch = new Stopwatch();
            PerformanceTest._testDelete(PerformanceTest._linkedList, repOp);
            PerformanceTest._printResult(stopwatch, repOp);
        }
    }

    private static void _testAllDeleteRandom(int repExp, int repOp, int n) {
        System.out.print("ArrayList\n");
        for (int i = 0; i < repExp; ++i) {
            PerformanceTest._resetLists(n);
            Stopwatch stopwatch = new Stopwatch();
            PerformanceTest._testDeleteRandom(PerformanceTest._myList, repOp);
            PerformanceTest._printResult(stopwatch, repOp);
        }
        System.out.print("LinkedList\n");
        for (int i = 0; i < repExp; ++i) {
            PerformanceTest._resetLists(n);
            Stopwatch stopwatch = new Stopwatch();
            PerformanceTest._testDeleteRandom(PerformanceTest._linkedList, repOp);
            PerformanceTest._printResult(stopwatch, repOp);
        }
    }

    private static void _testAllInsert(int repExp, int repOp, int n) {
        System.out.print("ArrayList\n");
        for (int i = 0; i < repExp; ++i) {
            PerformanceTest._resetLists(n);
            Stopwatch stopwatch = new Stopwatch();
            PerformanceTest._testInsert(PerformanceTest._myList, repOp);
            PerformanceTest._printResult(stopwatch, repOp);
        }
        System.out.print("LinkedList\n");
        for (int i = 0; i < repExp; ++i) {
            PerformanceTest._resetLists(n);
            Stopwatch stopwatch = new Stopwatch();
            PerformanceTest._testInsert(PerformanceTest._linkedList, repOp);
            PerformanceTest._printResult(stopwatch, repOp);
        }
    }

    private static void _printResult(Stopwatch stopwatch, int repOp) {
        double time = stopwatch.elapsedTime();
        System.out.printf("%10d\t%15.3f\t%10.3f\n", repOp, time, time / repOp);
    }

    private static void _testContains(AMyList<Integer> list, int repOp) {
        for (int i = 0; i < repOp; ++i) {
            list.contains(PerformanceTest._random.nextInt(list.length() - 1));
        }
    }

    private static void _testDelete(AMyList<Integer> list, int repOp) {
        for (int i = 0; i < repOp; ++i) {
            list.drop(PerformanceTest._random.nextInt(1));
        }
    }

    private static void _testDeleteRandom(AMyList<Integer> list, int repOp) {
        for (int i = 0; i < repOp; ++i) {
            list.drop(PerformanceTest._random.nextInt(list.length()));
        }
    }

    private static void _testInsert(AMyList<Integer> list, int repOp) {
        for (int i = 0; i < repOp; ++i) {
            list.add(PerformanceTest._random.nextInt(list.length() - 1), 1);
        }
    }

    private static void _fillLists(int n) {
        for (int i = 0; i < n; ++i) {
            PerformanceTest._myList.push(PerformanceTest._random.nextInt(n));
            PerformanceTest._linkedList.push(PerformanceTest._random.nextInt(n));
        }
    }

    enum Input {
        insert,
        deleteRand,
        delete,
        contains;
    }
}
