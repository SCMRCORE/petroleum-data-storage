package com.petroleumserver.mapper;

import com.github.pagehelper.Page;
import com.petroleumpojo.dto.FuZaDTO;
import com.petroleumpojo.dto.JiBenDTO;
import com.petroleumpojo.dto.JingShenDTO;
import com.petroleumpojo.dto.ZuanTouDTO;
import com.petroleumpojo.entity.FuZa;
import com.petroleumpojo.entity.JiBen;
import com.petroleumpojo.entity.JingShen;
import com.petroleumpojo.entity.ZuanTou;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface petroleumMapper {
    void addJinShenByList(List<JingShen> jingshenss);


    void addJiBenByList(List<JiBen> jiBen);

    void addFuZaByList(List<FuZa> fuza);

    void addZuanTouByList(List<ZuanTou> zuantou);

    Page<JiBen> searchjb(String wellName, String oilFieldName, String contractor);
    Page<JingShen> searchjs(String WellName, String PrimaryWellType, String WellType);

    Page<FuZa> searchfz(String wellName, String primaryWellType, String wellType);

    Page<ZuanTou> searchzt(String wellName, String primaryWellType, String wellType);

    void addJS(List<JingShen> jingShen);

    void addJB(List<JiBen> jiBen);

    void addFZ(List<FuZa> fuza);

    void addZT(List<ZuanTou> zuantou);

    void updateStatusJS(JingShenDTO jsDto);

    void updateStatusJB(JiBenDTO jbDto);

    void updateStatusFZ(FuZaDTO fzDto);

    void updateStatusZT(ZuanTouDTO ztDto);

    void updateJS(Integer onlyKey, JingShenDTO jsDto);
}
