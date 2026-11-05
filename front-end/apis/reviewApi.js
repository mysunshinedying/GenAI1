import api from "@/apis/axios";

export async function getReviewList(pageNo = 1) {
  return await api.get("/review/list", {
    params: { pageNo },
  });
}

export async function writeReview(reviewWriteRequest) {
  const formData = new FormData();
  formData.append("title", reviewWriteRequest.title);
  formData.append("content", reviewWriteRequest.content);
  formData.append("writer", reviewWriteRequest.writer);
  formData.append("productId", reviewWriteRequest.productId);

  if (reviewWriteRequest.file) {
    formData.append("file", reviewWriteRequest.file);
  }

  return await api.post("/review/write", formData);
}
