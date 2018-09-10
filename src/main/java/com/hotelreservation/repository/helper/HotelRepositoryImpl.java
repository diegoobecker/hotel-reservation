package com.hotelreservation.repository.helper;

import com.hotelreservation.model.Hotel;
import com.hotelreservation.repository.filter.HotelFilter;
import org.springframework.stereotype.Component;
import org.springframework.util.NumberUtils;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class HotelRepositoryImpl implements HotelRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Hotel> filter(HotelFilter filter) {

        final StringBuilder sb = new StringBuilder();
        final Map<String, Object> params = new HashMap<>();

        sb.append(" SELECT bean FROM Hotel bean WHERE 1=1 ");

        if(StringUtils.hasText(filter.getTitle())) {
            sb.append(" AND bean.title LIKE :title ");
            params.put("title", "%" + filter.getTitle() + "%");
        }

        if(StringUtils.hasText(filter.getCnpj())) {
            sb.append(" AND bean.cnpj LIKE :cnpj ");
            params.put("cnpj", "%" + filter.getCnpj() + "%");
        }

        if(StringUtils.hasText(Integer.toString(filter.getRating()))) {
            sb.append(" AND bean.rating = :rating ");
            params.put("rating", filter.getRating());
        }


        Query query = manager.createQuery(sb.toString(), Hotel.class);

        for(Map.Entry<String, Object> param : params.entrySet()) {
            query.setParameter(param.getKey(), param.getValue());
        }
        return query.getResultList();
    }
}
