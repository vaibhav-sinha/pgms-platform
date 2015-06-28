package com.pgms.shared.util;

import org.dozer.DozerBeanMapper;
import org.springframework.data.mapping.model.MappingException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by user-1 on 28/6/15.
 */
@Component
public class Mapper extends DozerBeanMapper {

    public <S, T> List<T> mapAsList(Collection<S> source, Class<T> destinationClass)
            throws MappingException {
        if (source == null) {
            return null;
        }
        return source.stream().map(o -> map(o, destinationClass)).collect(Collectors.toList());
    }

    @Override public <T> T map(Object source, Class<T> destinationClass) throws MappingException {
        if (source == null) {
            return null;
        }
        return super.map(source, destinationClass);
    }
}
