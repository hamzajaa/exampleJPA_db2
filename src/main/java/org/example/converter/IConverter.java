package org.example.converter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public interface IConverter<E, D> {

      E toEntity(D dto);

      D toDto(E entity);

//    public List<E> toEntity(List<D> dtos) {
//        if (dtos == null || dtos.isEmpty()) {
//            return Collections.emptyList();
//        } else {
//            List<E> entities = new ArrayList<>();
//            for (D dto : dtos) {
//                entities.add(toEntity(dto));
//            }
//            return entities;
//        }
//    }
}
