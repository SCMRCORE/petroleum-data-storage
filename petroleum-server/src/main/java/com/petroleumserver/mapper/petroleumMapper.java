package com.petroleumserver.mapper;

import com.github.pagehelper.Page;
import com.petroleumpojo.dto.*;
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

    Page<JiBen> searchjb(JiBenSearchPageDTO jiBenSearchPageDTO);
    Page<JingShen> searchjs(JingShenSearchPageDTO jingShenSearchPageDTO);

    Page<FuZa> searchfz(FuZaSearchPageDTO fuZaSearchPageDTO);

    Page<ZuanTou> searchzt(ZuanTouSearchPageDTO zuanTouSearchPageDTO);

    void addJS(List<JingShen> jingShen);

    void addJB(List<JiBen> jiBen);

    void addFZ(List<FuZa> fuza);

    void addZT(List<ZuanTou> zuantou);

    void updateStatusJS(Integer OnlyKey);

    void updateStatusJB(Integer OnlyKey);

    void updateStatusFZ(Integer OnlyKey);

    void updateStatusZT(Integer OnlyKey);


    void updateJS(JingShenDTO jsDto);

    void updateJB(JiBenDTO jbDto);

    void updateFZ(FuZaDTO fzDto);

    void updateZT(ZuanTouDTO ztDto);
}
