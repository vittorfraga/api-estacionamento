package com.vittorfraga.domain.parkinglot;

import com.vittorfraga.domain.pagination.Pagination;
import com.vittorfraga.domain.pagination.SearchQuery;

import java.util.List;
import java.util.Optional;

public interface ParkingLotGateway {

    ParkingLot create(ParkingLot aParkingLot);

    void deleteById(ParkingLotID anId);

    Optional<ParkingLot> findById(ParkingLotID anId);

    ParkingLot update(ParkingLot aParkingLot);

    Pagination<ParkingLot> findAll(SearchQuery aQuery);

    List<ParkingLot> existsByIds(Iterable<ParkingLotID> ids);
}
