package com.petroleumserver.mapper;

import com.github.pagehelper.Page;
import com.petroleumpojo.entity.FuZa;
import com.petroleumpojo.entity.JiBen;
import com.petroleumpojo.entity.JingShen;
import com.petroleumpojo.entity.ZuanTou;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface petroleumMapper {
    void addJinShenByList(List<JingShen> jingshenss);

    Page<JingShen> search(String WellName, String PrimaryWellType, String WellType);

    void addJiBenByList(List<JiBen> jiBen);

    void addFuZaByList(List<FuZa> fuza);

    void addZuanTouByList(List<ZuanTou> zuantou);
}
