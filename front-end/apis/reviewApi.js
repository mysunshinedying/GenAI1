import api from "@/apis/axios";

export async function getReviewList(pageNo = 1) {
  return await api.get("/review/list", {
    params: { pageNo },
  });
}

export async function reviewWrite(formData) {
  return await api.post("/review/write", formData);
}

export async function reviewDetail(reviewId) {
  return await api.get("/review/detail", {
    params: { reviewId },
    headers: {
      "Authorization": `Bearer ${localStorage.getItem("accessToken")}`
    }
  })
}

export async function reviewUpdate(formData) {
  return await api.put("/review/update", formData);
}

export function getReviewFileUrl(reviewId) {
  return api.getUri({
    url: "/review/file",
    params: { reviewId },
  });
}

export async function reviewDelete(reviewId) {
    return await api.delete("/review/delete", {
        params: { reviewId },
    })
}

//
// export async function writeReview(reviewWriteRequest) {
//   const formData = new FormData();
//   formData.append("title", reviewWriteRequest.title);
//   formData.append("content", reviewWriteRequest.content);
//   formData.append("writer", reviewWriteRequest.writer);
//   formData.append("productId", reviewWriteRequest.productId);
//
//   if (reviewWriteRequest.file) {
//     formData.append("file", reviewWriteRequest.file);
//   }
//
//   return await api.post("/review/write", formData);
// }
