package com.huterox.whitehole.whiteholeuser.service.surface;

public interface MailService {
    /**
     * 发送纯文本邮件
     * @param to
     * @param subject
     * @param text
     */
    public void sendTextMailMessage(String to,String subject,String text);

    /**
     * 发送html邮件
     * @param to
     * @param subject
     * @param content
     */
    public void sendHtmlMailMessage(String to,String subject,String content);
    /**
     * 发送带附件的邮件
     * @param to      邮件收信人
     * @param subject 邮件主题
     * @param content 邮件内容
     * @param filePath 附件路径
     */
    public void sendAttachmentMailMessage(String to,String subject,String content,String filePath);

    /**
     * 发送邮箱验证码
     * @param to
     * @param code
     */
    public void sendCodeMailMessage(String to,String code);
}
