package adp_1;

import java.util.HashMap;
import java.util.Random;
import java.util.function.Function;

public abstract class PerformanceTest {
    private static HashMap<String, AMyList<Integer>> _myLists = new HashMap<>();
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
        // don't delete this line or forEach will not work;
        PerformanceTest._resetLists(n);
        System.out.printf("%10s\t%16s\t%10s\n", "Wdh", "Zeit(ms)", "Zeit/repOp(ms)");
        PerformanceTest._myLists.forEach((listName, list) -> {
            System.out.printf("%s\n", listName);
            for (int repoExpNo = 0; repoExpNo < repExp; ++repoExpNo) {
                // reset list for each run n+1
                if (repoExpNo > 0) {
                    PerformanceTest._resetLists(n);
                }
                Function<Void, Void> fn = PerformanceTest._mapFunction(operation, list);

                // start to stop time
                Stopwatch stopwatch = new Stopwatch();
                for (int repoOpNo = 0; repoOpNo < repOp; ++repoOpNo) {
                    fn.apply(null);
                }
                PerformanceTest._printResult(stopwatch, repOp);
            }
        });
    }

    private static Function<Void, Void> _mapFunction(Input operation, AMyList<Integer> list) {
        switch (operation) {
            case insert:
                return x -> {
                    list.add(PerformanceTest._random.nextInt(list.length() - 1), 1);
                    return null;
                };
            case contains:
                return x -> {
                    list.contains(PerformanceTest._random.nextInt(list.length() - 1));
                    return null;
                };
            case delete:
                return x -> {
                    list.drop(PerformanceTest._random.nextInt(1));
                    return null;
                };
            case deleteRand:
                return x -> {
                    list.drop(PerformanceTest._random.nextInt(list.length()));
                    return null;
                };
            default:
                throw new Error("Wrong operation \"" + operation + "\"! Use: " + PerformanceTest._inputsToString());
        }
    }

    private static void _resetLists(int n) {
        PerformanceTest._myLists.put("ArrayList", new MyList<>());
        PerformanceTest._myLists.put("LinkedList", new LinkedList<>());
        PerformanceTest._fillLists(n);
    }

    private static void _printResult(Stopwatch stopwatch, int repOp) {
        double time = stopwatch.elapsedTime();
        System.out.printf("%10d\t%15.3f\t%10.3f\n", repOp, time, time / repOp);
    }

    private static void _fillLists(int n) {
        PerformanceTest._myLists.forEach((listName, list) -> {
            for (int i = 0; i < n; ++i) {
                list.push(PerformanceTest._random.nextInt(n));
            }
        });
    }

    enum Input {
        insert,
        deleteRand,
        delete,
        contains;
    }
}
