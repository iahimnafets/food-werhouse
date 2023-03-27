package com.food.warehouse.repository;

import com.food.warehouse.entities.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, Long> {

    List<Shipment> findByCarrierContaining(String carrier);

    List<Shipment> findByTrackingNumber(String trackingNumber);

}
