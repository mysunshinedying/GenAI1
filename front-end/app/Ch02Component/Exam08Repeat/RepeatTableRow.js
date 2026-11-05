export default function RepeatTableRow() {
  const boards = [
    { bno: 1, btitle: "제목1", bwriter: "user1", bdate: "2026.11.03", bhitcount: 0 },
    { bno: 2, btitle: "제목2", bwriter: "user2", bdate: "2026.11.03", bhitcount: 12 },
    { bno: 3, btitle: "제목3", bwriter: "user3", bdate: "2026.11.03", bhitcount: 5 },
  ];

  return (
    <section className="overflow-hidden rounded-xl border border-slate-200 bg-white shadow-sm">
      <header className="flex items-center justify-between border-b border-slate-200 bg-slate-50 px-5 py-4">
        <div>
          <h2 className="text-lg font-bold text-slate-900">게시글 목록</h2>
          <p className="mt-1 text-sm text-slate-500">등록된 게시글을 확인할 수 있습니다.</p>
        </div>
        <span className="rounded-full bg-indigo-100 px-3 py-1 text-sm font-semibold text-indigo-700">
          {boards.length}개
        </span>
      </header>

      <div className="overflow-x-auto">
        <table className="w-full min-w-[640px] text-left text-sm">
          <thead className="bg-slate-100 text-xs uppercase tracking-wide text-slate-600">
            <tr>
              <th scope="col" className="px-5 py-3 text-center font-semibold">번호</th>
              <th scope="col" className="px-5 py-3 font-semibold">제목</th>
              <th scope="col" className="px-5 py-3 font-semibold">작성자</th>
              <th scope="col" className="px-5 py-3 text-center font-semibold">작성일</th>
              <th scope="col" className="px-5 py-3 text-center font-semibold">조회수</th>
            </tr>
          </thead>
          <tbody className="divide-y divide-slate-100">
            {boards.map((board) => (
              <tr key={board.bno} className="transition-colors hover:bg-indigo-50/60">
                <td className="px-5 py-4 text-center tabular-nums text-slate-500">{board.bno}</td>
                <td className="px-5 py-4 font-medium text-slate-900">{board.btitle}</td>
                <td className="px-5 py-4 text-slate-600">{board.bwriter}</td>
                <td className="whitespace-nowrap px-5 py-4 text-center text-slate-500">{board.bdate}</td>
                <td className="px-5 py-4 text-center tabular-nums text-slate-600">{board.bhitcount}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </section>
  );
}
