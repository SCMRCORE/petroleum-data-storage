package com.petroleumserver.service.lmp;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.petroleumcommom.result.PageResult;
import com.petroleumcommom.utils.FuZaTool;
import com.petroleumcommom.utils.JiBenTool;
import com.petroleumcommom.utils.JinShenTool;
import com.petroleumcommom.utils.ZuanTouTool;
import com.petroleumpojo.dto.*;
import com.petroleumpojo.entity.FuZa;
import com.petroleumpojo.entity.JiBen;
import com.petroleumpojo.entity.JingShen;
import com.petroleumpojo.entity.ZuanTou;
import com.petroleumserver.mapper.petroleumMapper;
import com.petroleumserver.service.petroleumService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class petroleumServicelmp implements petroleumService {

    @Resource
    private petroleumMapper petroleumMapper;

    /**
     * 批量添加
     * @param file
     * @param company
     * @param num
     * @throws IOException
     */
    @Override
    public void addByList(MultipartFile file, String company, Integer num) throws IOException {
        //井口表
        if(num == 1) {
            InputStream inputStream = file.getInputStream();
            ExcelReader reader = ExcelUtil.getReader(inputStream);
            ExcelReader NewReader = JinShenTool.transition(reader);
            List<JingShenDTO> jingShenDto = NewReader.readAll(JingShenDTO.class);

            List<JingShen> jingShen = new ArrayList<>();
            for(JingShenDTO jingShen1 : jingShenDto){
                JingShen jingShenOne = new JingShen();
                BeanUtils.copyProperties(jingShen1, jingShenOne);
                jingShen.add(jingShenOne);
            }
            for (JingShen jingShenOne : jingShen) {
                jingShenOne.setCompany(company);
                jingShenOne.setStatus(1);
            }
            petroleumMapper.addJinShenByList(jingShen);
        }
        //基本信息表
        if(num == 2) {
            InputStream inputStream = file.getInputStream();
            ExcelReader reader = ExcelUtil.getReader(inputStream);
            ExcelReader NewReader = JiBenTool.transition(reader);
            List<JiBenDTO> jiBenDto = NewReader.readAll(JiBenDTO.class);

            List<JiBen> jiBen = new ArrayList<>();
            for(JiBenDTO jiBen1 : jiBenDto){
                JiBen jiBenOne = new JiBen();
                BeanUtils.copyProperties(jiBen1, jiBenOne);
                jiBen.add(jiBenOne);
            }
            for (JiBen jiBenOne : jiBen) {
                jiBenOne.setCompany(company);
                jiBenOne.setStatus(1);
            }
            petroleumMapper.addJiBenByList(jiBen);
        }
        //复杂情况表
        if(num == 3) {
            InputStream inputStream = file.getInputStream();
            ExcelReader reader = ExcelUtil.getReader(inputStream);
            ExcelReader NewReader = FuZaTool.transition(reader);
            List<FuZaDTO> fuzaDto = NewReader.readAll(FuZaDTO.class);

            List<FuZa> fuza = new ArrayList<>();
            for(FuZaDTO fuza1 : fuzaDto){
                FuZa fuzaOne = new FuZa();
                BeanUtils.copyProperties(fuza1, fuzaOne);
                fuza.add(fuzaOne);
            }
            for (FuZa fuzaOne : fuza) {
                fuzaOne.setCompany(company);
                fuzaOne.setStatus(1);
            }
            petroleumMapper.addFuZaByList(fuza);
        }
        //钻头表
        if(num == 4) {
            InputStream inputStream = file.getInputStream();
            ExcelReader reader = ExcelUtil.getReader(inputStream);
            ExcelReader NewReader = ZuanTouTool.transition(reader);
            List<ZuanTouDTO> zuantouDto = NewReader.readAll(ZuanTouDTO.class);

            List<ZuanTou> zuantou = new ArrayList<>();
            for(ZuanTouDTO zuantou1 : zuantouDto){
                ZuanTou zuantouOne = new ZuanTou();
                BeanUtils.copyProperties(zuantou1, zuantouOne);
                zuantou.add(zuantouOne);
            }
            for (ZuanTou zuanTouOne : zuantou) {
                zuanTouOne.setCompany(company);
                zuanTouOne.setStatus(1);
            }
            petroleumMapper.addZuanTouByList(zuantou);
        }
    }


    /**
     * 新增js
     * @param jsDto
     */
    @Override
    public void addJS(List<JingShenDTO> jsDto) {
        List<JingShen> jingShen = new ArrayList<>();
        for(JingShenDTO js1 : jsDto) {
            JingShen jingShen1 = new JingShen();
            BeanUtils.copyProperties(js1, jingShen1);
            jingShen1.setStatus(1);
            jingShen.add(jingShen1);
        }
        petroleumMapper.addJS(jingShen);
    }

    @Override
    public void addJB(List<JiBenDTO> jbDto) {
        List<JiBen> jiben = new ArrayList<>();
        for (JiBenDTO jb1 : jbDto) {
            JiBen jiBen1 = new JiBen();
            BeanUtils.copyProperties(jb1, jiBen1);
            jiBen1.setStatus(1);
            jiben.add(jiBen1);
        }
        petroleumMapper.addJB(jiben);
    }

    @Override
    public void addFZ(List<FuZaDTO> fzDto) {
        List<FuZa> fuZa = new ArrayList<>();
        for(FuZaDTO fz1 : fzDto) {
            FuZa fuza = new FuZa();
            BeanUtils.copyProperties(fz1, fuza);
            fuza.setStatus(1);
            fuZa.add(fuza);
        }
        petroleumMapper.addFZ(fuZa);
    }

    @Override
    public void addZT(List<ZuanTouDTO> ztDto) {
        List<ZuanTou> zuanTou = new ArrayList<>();
        for(ZuanTouDTO zt1 : ztDto) {
            ZuanTou zuantou = new ZuanTou();
            BeanUtils.copyProperties(zt1, zuantou);
            zuantou.setStatus(1);
            zuanTou.add(zuantou);
        }
        petroleumMapper.addZT(zuanTou);
    }

    /**
     * 搜索js
     * @param jsSPDto
     * @return
     */
    @Override
    public PageResult searchjs(JingShenSearchPageDTO jsSPDto) {
        PageHelper.startPage(jsSPDto.getPageIndex(), jsSPDto.getPageSize());
        Page<JingShen> page = petroleumMapper.searchjs(jsSPDto.getWellName(), jsSPDto.getPrimaryWellType(), jsSPDto.getWellType());
        return new PageResult(page.getTotal(), page.getResult());
    }

    /**
     * 搜索jb
     * @param jbSPDto
     * @return
     */
    @Override
    public PageResult searchjb(JiBenSearchPageDTO jbSPDto) {
        PageHelper.startPage(jbSPDto.getPageIndex(), jbSPDto.getPageSize());
        Page<JiBen> page = petroleumMapper.searchjb(jbSPDto.getWellName(), jbSPDto.getOilFieldName(), jbSPDto.getContractor());
        return new PageResult(page.getTotal(), page.getResult());
    }

    /**
     * 搜索fz
     * @param fzSPDto
     * @return
     */
    @Override
    public PageResult searchfz(FuZaSearchPageDTO fzSPDto) {
        PageHelper.startPage(fzSPDto.getPageIndex(), fzSPDto.getPageSize());
        Page<FuZa> page = petroleumMapper.searchfz(fzSPDto.getWellName(), fzSPDto.getPrimaryWellType(), fzSPDto.getWellType());
        return new PageResult(page.getTotal(), page.getResult());
    }

    /**
     * 搜索zt
     * @param ztSPDto
     * @return
     */
    @Override
    public PageResult searchzt(ZuanTouSearchPageDTO ztSPDto) {
        PageHelper.startPage(ztSPDto.getPageIndex(), ztSPDto.getPageSize());
        Page<ZuanTou> page = petroleumMapper.searchzt(ztSPDto.getWellName(), ztSPDto.getPrimaryWellType(), ztSPDto.getWellType());
        return new PageResult(page.getTotal(), page.getResult());
    }


//    /**
//     * 软删除js
//     * @param jsDto
//     */
//    @Override
//    public void updateStatusJS(JingShenDTO jsDto) {
//        petroleumMapper.updateStatusJS(jsDto);
//    }
//
//    @Override
//    public void updateStatusJB(JiBenDTO jbDto) {
//        petroleumMapper.updateStatusJB(jbDto);
//    }
//
//    @Override
//    public void updateStatusFZ(FuZaDTO fzDto) {
//        petroleumMapper.updateStatusFZ(fzDto);
//    }
//
//    @Override
//    public void updateStatusZT(ZuanTouDTO ztDto) {
//        petroleumMapper.updateStatusZT(ztDto);
//    }

    /**
     * 软删除
     * @param num
     * @param onlyKey
     */
    @Override
    public void updateStatus(Integer num, Integer onlyKey) {
        if(num==1){
            petroleumMapper.updateStatusJS(onlyKey);
        }
        if(num==2){
            petroleumMapper.updateStatusJB(onlyKey);
        }
        if(num==3){
            petroleumMapper.updateStatusFZ(onlyKey);
        }
        if(num==4){
            petroleumMapper.updateStatusZT(onlyKey);
        }
    }



    @Override
    public void updateJS(Integer OnlyKey, JingShenDTO jsDto) {
        jsDto.setOnlyKey(OnlyKey);
        petroleumMapper.updateJS(jsDto);
    }




}
