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
     * @param company
     */
    @Override
    public void addJS(JingShenDTO jsDto, String company) {
        JingShen jingShen = new JingShen();
        BeanUtils.copyProperties(jsDto, jingShen);
        jingShen.setCompany(company);
        jingShen.setStatus(1);
        petroleumMapper.addJS(jingShen);
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
        Page<FuZa> page = petroleumMapper.searchzt(ztSPDto.getWellName(), ztSPDto.getPrimaryWellType(), ztSPDto.getWellType());
        return new PageResult(page.getTotal(), page.getResult());
    }
}
