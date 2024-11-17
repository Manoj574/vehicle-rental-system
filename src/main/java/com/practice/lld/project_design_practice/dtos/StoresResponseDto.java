package com.practice.lld.project_design_practice.dtos;

import com.practice.lld.project_design_practice.models.Store;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
public class StoresResponseDto {

    private List<StoreDto> stores;

    private String errorMessage;

    private ResponseStatus responseStatus;

    public static StoresResponseDto getSuccessfulResponse(final List<Store> stores) {

        return StoresResponseDto.builder()
                .stores(stores.stream().map(store -> StoreDto.builder().id(store.getId())
                        .city(store.getCity())
                        .address(store.getAddress())
                        .name(store.getName())
                        .build()).collect(Collectors.toList())
                        )
                .responseStatus(ResponseStatus.SUCCESSFUL)
                .build();
    }

    public static StoresResponseDto getFailureResponse(final String errorMessage) {

        return StoresResponseDto.builder()
                .errorMessage(errorMessage)
                .responseStatus(ResponseStatus.FAILED)
                .build();
    }
}
