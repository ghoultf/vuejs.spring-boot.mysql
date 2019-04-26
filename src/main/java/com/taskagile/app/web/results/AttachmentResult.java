package com.taskagile.app.web.results;

import com.taskagile.app.domain.common.file.FileUrlCreator;
import com.taskagile.app.domain.model.attachment.Attachment;
import com.taskagile.app.utils.ImageUtils;
import org.springframework.http.ResponseEntity;

public class AttachmentResult {

  public static ResponseEntity<ApiResult> build(Attachment attachment, FileUrlCreator fileUrlCreator) {
    String fileUrl = fileUrlCreator.url(attachment.getFilePath());
    ApiResult apiResult = ApiResult.blank().add("id", attachment.getId().value())
        .add("fileName", attachment.getFileName()).add("fileType", attachment.getFileType()).add("fileUrl", fileUrl)
        .add("userId", attachment.getUserId().value()).add("createdDate", attachment.getCreatedDate().getTime());

    if (attachment.isThumbnailCreated()) {
      apiResult.add("previewUrl", ImageUtils.getThumbnailVersion(fileUrl));
    }
    return Result.ok(apiResult);
  }
}
