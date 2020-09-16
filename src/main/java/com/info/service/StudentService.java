package com.info.service;

import com.github.tobato.fastdfs.domain.StorePath;
import com.info.common.ReturnValue;
import com.info.common.sysenum.ApplyEnum;
import com.info.common.sysenum.StateMsg;
import com.info.dto.*;
import com.info.entity.*;
import com.info.entity.converter.Converter;
import com.info.exception.SystemException;
import com.info.formbean.PageBean;
import com.info.formbean.ProcessFormBean;
import com.info.mapper.*;
import com.info.util.DateUtil;
import com.info.util.EncryptUtil;
import com.info.util.FastClientUtil;
import com.info.util.RandomUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author : yue
 * @Date : 2020/7/8 / 20:21
 */
@Service
public class StudentService {
    @Resource
    private StudentInfoMapper studentMapper;

    @Resource
    private ScoreInfoMapper scoreMapper;

    @Resource
    private AssessMapper assessMapper;


    @Autowired
    private AbilityMapper abilityMapper;

    @Autowired
    private FastClientUtil clientUtil;

    @Autowired
    private ApplyProjectMapper applyProjectMapper;

    @Autowired
    private ApplyMapper applyMapper;

//    @Autowired
//    private CETScoreMapper cetScoreMapper;


    private String[] typeList = new String[]{
            "creative", "profession", "leader", "practice", "special"
    };

    private Converter<ScoreEntity> scoreConverter = new Converter<>();

    private Converter<Student> studentConverter = new Converter<>();

    private Converter<ApplyProjectEntity> applyEntityConverter = new Converter<>();

    private Converter<CETScoreEntity> cetScoreConverter = new Converter<>();


    public StudentInfoDto getStudentInfo(String id, String password) throws Exception {
        Student entity = studentMapper.getStudentById(id);
        if (null == entity) {
            throw new SystemException(StateMsg.StateMsg_205);
        }
        //去掉加密过程
        //String encryptCode = EncryptUtil.encryptMD5(password);
        if (!entity.getPassword().equals(password)) {
            throw new SystemException(StateMsg.StateMsg_203);
        }

        return studentConverter.clone(entity, StudentInfoDto.class);

    }


    public String updatePassword(String id, String password, String newPassword)
            throws SystemException {
        String pwd = studentMapper.getPassword(id);
        if (pwd == null || !pwd.equals(EncryptUtil.encryptMD5(password))) {
            throw new SystemException(StateMsg.StateMsg_202);
        }
        String updatePwd = EncryptUtil.encryptMD5(newPassword);
        int res = studentMapper.updatePassword(id, updatePwd);
        if (res <= 0) {
            throw new SystemException(StateMsg.StateMsg_500);
        }
        return StateMsg.StateMsg_200.getMsg();
    }

    public List<StudentInfoDto> getStudentList(String id, String grade) {

        List<Student> students = studentMapper.selectStudents(id, grade);
        return students.stream().map(item ->
                studentConverter.clone(item, StudentInfoDto.class))
                .collect(Collectors.toList());
    }


    public ReturnValue<Student> getStudentList(PageBean page)
            throws SystemException {
        if (page.getCurrentPage() <= 0) {
            throw new SystemException(StateMsg.StateMsg_200);
        }
        ReturnValue<Student> result = new ReturnValue<>();
        int start = (page.getCurrentPage() - 1) * page.getPageSize();
        List<Student> students = studentMapper.pageSelectStudent(start, page.getPageSize());
        result.setList(students);
        return result;
    }


    public String changePassword(String id, String oldPassword)
            throws SystemException {
        String password = studentMapper.getPassword(id);
        if (!EncryptUtil.encryptMD5(password).equals(oldPassword)) {
            throw new SystemException(StateMsg.StateMsg_203);
        }
        return null;
    }


//
//    @ApiOperation("获取成绩")
//    public List<ScoreDTO> queryScoreById(String id) throws Exception {
//        List<ScoreEntity> scores = scoreMapper.selectScoreById(id);
//        if (null == scores || scores.size() == 0) {
//            throw new SystemException(StateMsg.StateMsg_104);
//        }
//        return scores.stream().map(item ->
//                scoreConverter.clone(item, ScoreDTO.class))
//                .collect(Collectors.toList());
//    }


    public Map<String, List<ScoreDTO>> groupQueryScore(String id, String semester)
            throws SystemException {
        List<ScoreEntity> scores;
        if (semester != null) {
            scores = scoreMapper.selectScoreWithSemester(id, semester);
        } else {
            scores = scoreMapper.selectScoreById(id);
        }
        if (null == scores || scores.size() == 0) {
            return null;
        }

        return scores.stream().map(item ->
                scoreConverter.clone(item, ScoreDTO.class))
                .collect(Collectors.groupingBy(ScoreDTO::getCourseType));
    }

    /**
     * 上传学生照片
     *
     * @param file image
     * @param id   student_id
     * @return void
     * @throws Exception void
     */
    public FileDTO uploadFile(MultipartFile file, String id) throws Exception {
        FileDTO res = new FileDTO();
        StorePath storePath = clientUtil.upload(file);
        ImageEntity image = new ImageEntity();
        image.setName(file.getName());
        image.setPath(storePath.getPath());
        image.setImgGroup(storePath.getGroup());

        return res;
    }

    public Map<String, List<ApplyProjectDTO>> getApplyList(String sort) throws Exception {
        List<ApplyProjectEntity> entities = applyProjectMapper.selectAll(sort);
        if (entities == null) {
            throw new SystemException(StateMsg.StateMsg_104);
        }
        return entities.stream().map(item ->
                applyEntityConverter.clone(item, ApplyProjectDTO.class))
                .collect(Collectors.groupingBy(ApplyProjectDTO::getMenuName));
    }

    //提交申请
    public String submitApply(ApplyDTO applyDTO, MultipartFile file)
            throws Exception {
        ApplyEntity entity = new ApplyEntity();
        if (file != null) {
            StorePath upload = clientUtil.upload(file);
            String imgPath = upload.getGroup() + "/" + upload.getPath();
            entity.setImage(imgPath);
        }

        String id = RandomUtil.getRandomStr(20);
        entity.setId(id);
        entity.setStudentId(applyDTO.getStudentId());
        entity.setApplyId(applyDTO.getApplyId());
        entity.setApplyName(applyDTO.getApplyName());
        entity.setFormData(applyDTO.getFormData());
        entity.setFormTemp(applyDTO.getFormTemp());

        entity.setScore(applyDTO.getScore());
        entity.setApplyState(ApplyEnum.APPLYING.name());

        Integer insert = applyMapper.insert(entity);
        return insert == 1 ? StateMsg.StateMsg_200.getMsg() : StateMsg.StateMsg_500.getMsg();
    }


//    public Map<String, List<CETScoreDTO>> getAllCETScore(String id) throws Exception {
//        List<CETScoreEntity> cet = cetScoreMapper.selectByIdAndType(id);
//
//        if (cet == null) {
//            throw new SystemException(StateMsg.StateMsg_104);
//        }
//
//        return cet.stream()
//                .map(item -> cetScoreConverter.clone(item, CETScoreDTO.class))
//                .collect(Collectors.groupingBy(CETScoreDTO::getCetLevel));
//    }


    //获取进度列表
    public List<ApplyDTO> getProcessList(ProcessFormBean formBean)
            throws Exception {
        List<ApplyEntity> entities;
        if (formBean.getState() == null) {
            entities = applyMapper.selectApplyInfo(formBean.getStudentId());
        } else {
            entities = applyMapper.selectApplyByState(formBean.getStudentId(), formBean.getState());
        }
        if (entities == null || entities.isEmpty()) {
            return null;
        }
        return entities.stream().map(item -> {
            ApplyDTO applyDTO = new ApplyDTO();
            applyDTO.setId(item.getId());
            applyDTO.setApplyName(item.getApplyName());
            applyDTO.setFormData(item.getFormData());
            applyDTO.setFormTemp(item.getFormTemp());
            applyDTO.setImage(item.getImage());
            applyDTO.setApplyState(item.getApplyState());
            return applyDTO;
        }).collect(Collectors.toList());

    }

    public String getFormTemp(String menuId, String projectId) throws Exception {
        String formTemp = applyProjectMapper.selectTempInProject(projectId);
        if (formTemp == null || formTemp.equals("")) {
            formTemp = applyProjectMapper.selectFormTemp(menuId);
        }
        return formTemp;
    }

    //获得综合成绩排名，六大类每一类的前三名
    public Map<String, List<AbilityEntity>> getRank(String studentId,
                                                    String semester)
            throws Exception {
        String classYear = studentId.substring(0, 2);

        Map<String, List<AbilityEntity>> map = new HashMap<>();
        for (int i = 0; i < this.typeList.length; i++) {
            List<AbilityEntity> list = abilityMapper.getComprehensiveRank(semester, classYear,this.typeList[i], 3);
            map.put(this.typeList[i], list);
        }
        return map;
    }

    //获取个性化榜单
    public AbilityEntity getPersonalList(String studentId) throws Exception {
        String exampleId = abilityMapper.selectPersonalizedId(studentId);
        int year = DateUtil.getNowYear();
        //默认当前学期
        return  abilityMapper.selectAbilityAllScore(exampleId, year + "-" + (year + 1));
    }


    public AbilityEntity getAbilityAllScore(String studentId) throws Exception{
        int year = DateUtil.getNowYear();
        return  abilityMapper.selectAbilityAllScore(studentId, year + "-" + (year + 1));
    }
    public byte[] downloadFile(String group, String path)
            throws Exception {
        return clientUtil.download(group, path);
    }
}
