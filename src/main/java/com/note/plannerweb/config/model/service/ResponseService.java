package com.note.plannerweb.config.model.service;

import com.note.plannerweb.config.model.response.CommonResponse;
import com.note.plannerweb.config.model.response.CommonResult;
import com.note.plannerweb.config.model.response.ListResult;
import com.note.plannerweb.config.model.response.SingleResult;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResponseService {

    //단일건 결과 처리 메소드
    public <T> SingleResult<T> getSingleResult(T data){
        SingleResult<T> singleResult=new SingleResult<>();
        singleResult.setData(data);
        setSuccessResult(singleResult);
        return singleResult;
    }

    //복수건 결과 처리 메소드
    public <T> ListResult<T> getListResult(List<T> list){
        ListResult<T> listResult=new ListResult<>();
        listResult.setData(list);
        setSuccessResult(listResult);
        return listResult;
    }

    //성공 결과만 처리
    public CommonResult getSuccessResult(){
        CommonResult commonResult=new CommonResult();
        setSuccessResult(commonResult);
        return commonResult;
    }

    //실패 결과만 처리
    public CommonResult getFailResult(){
        CommonResult commonResult=new CommonResult();
        setFailResult(commonResult);
        return commonResult;
    }

    //API 요청 성공 시 응답 모델 성공 데이터로 세팅
    private void setSuccessResult(CommonResult commonResult){
        commonResult.setSuccess(true);
        commonResult.setCode(CommonResponse.SUCCESS.getCode());
        commonResult.setMsg(CommonResponse.SUCCESS.getMsg());
    }


    //API 요청 실패 시 응답 모델 실패 데이터로 세팅
    private void setFailResult(CommonResult commonResult){
        commonResult.setSuccess(false);
        commonResult.setCode(CommonResponse.FAIL.getCode());
        commonResult.setMsg(CommonResponse.FAIL.getMsg());
    }
}
