package com.example.blood.repository;

import com.example.blood.dto.PatientDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Repository
public class JdbcTemplatePatientRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcTemplatePatientRepository(DataSource dataSource) {
       jdbcTemplate=new JdbcTemplate(dataSource);
    }
    //쿼리 결과를 자바 객체에 매핑하는 코드임
    private RowMapper<PatientDto> PatientDtoRowMapper() {
        return (rs, rowNum) -> {
            PatientDto patientDto = new PatientDto();
            patientDto.setPatientId(rs.getString("환자ID"));
            patientDto.setName(rs.getString("이름"));
            java.sql.Date birthDateSql = rs.getDate("생년월일"); // 시간정보 없애기
            if (birthDateSql != null) {
                patientDto.setBirth(birthDateSql.toLocalDate()); // LocalDate로 변환(dto 멤버변수 형식 맞춰줌)
            }
            patientDto.setPhoneNumber(rs.getString("휴대폰번호"));
            patientDto.setGender(rs.getString("성별"));
            patientDto.setHospitalName(rs.getString("병원이름"));
            patientDto.setDiseaseName(rs.getString("병명"));
            return patientDto;
        };
    }
    public List<PatientDto> findAll() {
        return jdbcTemplate.query("select * from 환우", PatientDtoRowMapper());
    }
    public List<PatientDto> findByPatientName(String patientName) {
        return jdbcTemplate.query("select * from 환우 where 이름=?", PatientDtoRowMapper(),patientName);
    }

    public List<PatientDto> findByDiseaseName(String diseaseName) {
        return jdbcTemplate.query("select * from 환우 where 병명=?", PatientDtoRowMapper(),diseaseName);

    }
}
