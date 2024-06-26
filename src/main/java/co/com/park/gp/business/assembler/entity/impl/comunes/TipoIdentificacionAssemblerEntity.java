package co.com.park.gp.business.assembler.entity.impl.comunes;

import java.util.ArrayList;
import java.util.List;

import co.com.park.gp.business.assembler.entity.AssemblerEntity;
import co.com.park.gp.business.domain.comunes.TipoIdentificacionDomain;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.entity.comunes.TipoIdentificacionEntity;

public final class TipoIdentificacionAssemblerEntity
        implements AssemblerEntity<TipoIdentificacionDomain, TipoIdentificacionEntity> {

    private static final AssemblerEntity<TipoIdentificacionDomain, TipoIdentificacionEntity> instance = new TipoIdentificacionAssemblerEntity();

    private TipoIdentificacionAssemblerEntity() {
        super();
    }

    public static AssemblerEntity<TipoIdentificacionDomain, TipoIdentificacionEntity> getInstance() {
        return instance;
    }

    @Override
    public TipoIdentificacionDomain toDomain(final TipoIdentificacionEntity data) {
        var tipoIdentificacionEntityTmp = ObjectHelper.getObjectHelper().getDefaultValue(data,
                TipoIdentificacionEntity.build());
        return TipoIdentificacionDomain.build(tipoIdentificacionEntityTmp.getId(),
                tipoIdentificacionEntityTmp.getNombre());
    }

    @Override
    public List<TipoIdentificacionDomain> toDomainCollection(List<TipoIdentificacionEntity> entityCollection) {
        var entityCollectionTmp = ObjectHelper.getObjectHelper().getDefaultValue(entityCollection,
                new ArrayList<TipoIdentificacionEntity>());
        return entityCollectionTmp.stream().map(this::toDomain).toList();
    }

    @Override
    public TipoIdentificacionEntity toEntity(final TipoIdentificacionDomain domain) {
        var tipoIdentificacionDomainTmp = ObjectHelper.getObjectHelper().getDefaultValue(domain,
                TipoIdentificacionDomain.build());
        return TipoIdentificacionEntity.build().setId(tipoIdentificacionDomainTmp.getId())
                .setNombre(tipoIdentificacionDomainTmp.getNombre());
    }

    @Override
    public List<TipoIdentificacionEntity> toEntityCollection(List<TipoIdentificacionDomain> domainCollection) {
        var domainCollectionTmp = ObjectHelper.getObjectHelper().getDefaultValue(domainCollection,
                new ArrayList<TipoIdentificacionDomain>());
        return domainCollectionTmp.stream().map(this::toEntity).toList();
    }

}
