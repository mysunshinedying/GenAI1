import Link from "next/link";

export default function NotFound() {
    return (
        <main className="flex min-h-[60vh] items-center justify-center px-6 py-12">
            <section className="w-full max-w-lg rounded-2xl border border-slate-200 bg-white p-8 text-center shadow-sm">
                <p className="text-sm font-bold uppercase tracking-[0.2em] text-indigo-600">404 Not Found</p>
                <h1 className="mt-3 text-2xl font-bold text-slate-900">잘못된 URL 입력입니다.</h1>
                <p className="mt-3 text-slate-600">요청하신 페이지를 찾을 수 없습니다.</p>
                <Link
                    href="/"
                    className="mt-6 inline-flex rounded-lg bg-indigo-600 px-5 py-2.5 font-semibold text-white transition hover:bg-indigo-700 focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600"
                >
                    홈으로 돌아가기
                </Link>
            </section>
        </main>
    );
}
