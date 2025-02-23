package com.example.limepay.model.director;

import lombok.Builder;
import lombok.Data;

import java.util.Collection;

@Data
@Builder
public class DirectorsResponse {

    private Collection<String> directors;
}
