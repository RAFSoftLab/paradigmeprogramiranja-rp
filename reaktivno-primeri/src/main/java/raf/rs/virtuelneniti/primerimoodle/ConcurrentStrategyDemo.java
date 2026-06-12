package raf.rs.virtuelneniti.primerimoodle;

import java.util.List;
import java.util.concurrent.StructuredTaskScope;

public class ConcurrentStrategyDemo {

    public static Integer vratiRezultatInvokeAny() throws InterruptedException {
        try (var scope = StructuredTaskScope.open(StructuredTaskScope.Joiner.anySuccessfulResultOrThrow())){
            var br1Subtask = scope.fork(()-> vratiBroj(200));
            var br2Subtask= scope.fork(()-> vratiBroj(50));
            var br3Subtask= scope.fork(()-> vratiBroj(100));
            Integer rez = (Integer)scope.join();
            return rez;
        }
    }

    public static List<Integer> vratiRezultatInvokeAll() throws InterruptedException {
        try (var scope = StructuredTaskScope.open()){
            var br1Subtask = scope.fork(()-> vratiBroj(200));
            var br2Subtask= scope.fork(()-> vratiBroj(50));
            var br3Subtask= scope.fork(()-> vratiBroj(100));
            scope.join();
            return List.of(br1Subtask.get(),br2Subtask.get(),br3Subtask.get());
        }
    }

    private static int vratiBroj(int delay) throws InterruptedException {
          Thread.sleep(delay);
          return delay/10;
    }

    static void main() {
        try {
            System.out.println(vratiRezultatInvokeAll());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
