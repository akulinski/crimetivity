package com.akulinski.crimetivity.pointsaftyservice.core.domain;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@Document
public class LoadDataRequest implements Serializable {

    private String id;

    private String cityName;

    private String lat;

    private String lon;

    private RequestStatus requestStatus;
}
