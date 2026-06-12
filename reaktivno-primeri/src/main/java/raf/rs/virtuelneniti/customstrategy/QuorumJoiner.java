package raf.rs.virtuelneniti.customstrategy;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.StructuredTaskScope;
import java.util.concurrent.atomic.AtomicInteger;

public class QuorumJoiner<T> implements StructuredTaskScope.Joiner<T, List<T>> {
    private final int quorum;
    private final List<T> results = new CopyOnWriteArrayList<>();
    private final AtomicInteger completedCount = new AtomicInteger(0);

    public QuorumJoiner(int quorum) {
        this.quorum = quorum;
    }

    @Override
    public boolean onComplete(StructuredTaskScope.Subtask<? extends T> subtask) {
        if (subtask.state() == StructuredTaskScope.Subtask.State.SUCCESS) {
            results.add(subtask.get());
        }
        return completedCount.incrementAndGet() >= quorum;
    }

    @Override
    public List<T> result() {
        return List.copyOf(results);
    }
}

