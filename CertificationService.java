package com.bitplus.btsapi.service.certification;

import com.bitplus.btsapi.model.*;

public interface CertificationService {

    void sendEmail(EmailCertificateVo vo);

    void emailCertification(EmailCertificateVo vo);

    void cellphoneCertification(CellPhoneCertificateVo vo);

    void secondaryPasswordRegist(MembersVo vo);

    GoogleOTPCertificateVo googleOtpCertificationCreate(GoogleOTPCertificateVo vo);

    boolean googleOtpCertificatetionVerify(GoogleOTPCertificateVo vo);

    boolean scndPwVerify(GoogleOTPCertificateVo vo);

    void fileInsert (FileVo vo); //'신분증' db에 넣기

    void holdingIdFileInsert(FileVo vo); //'신분증 들고있는 사진' db에 넣기

    void etcImgFileInsert(FileVo vo); //'기타 서류 사진' db에 넣기
}
