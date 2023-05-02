package ru.pivovarov.transferservice.repository;

import org.springframework.stereotype.Repository;
import ru.pivovarov.transferservice.model.Transfer;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class TransferRepository {
    private static ConcurrentMap<Integer, Transfer> transferList = new ConcurrentHashMap<>();
    private static AtomicInteger id = new AtomicInteger(0);

    public Integer addTransfer(Transfer transfer) {
        int transferId = id.incrementAndGet();
        transferList.put(transferId, transfer);
        return transferId;
    }

    public Transfer getTransfer(String id) {
        return transferList.get(Integer.valueOf(id));
    }
}
