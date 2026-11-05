import Image from "next/image";

export default function Exam04InlineStyle() {

    return (
        <section className="m-6 overflow-hidden rounded-lg border border-gray-200 bg-white shadow-sm">
            <header className="border-b border-gray-200 bg-gray-50 px-5 py-3">
                <h1 className="text-lg font-semibold">Exam04InlineStyle</h1>
            </header>
            <div className="p-5">
                <div style={{backgroundColor: "yellow", color: "red", fontSize: "24px"}}>
                    인라인 스타일 적용
                </div>
            </div>
        </section>
    )
}