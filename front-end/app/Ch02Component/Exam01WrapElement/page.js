import ChildComponentB from "@/app/Ch02Component/Exam01WrapElement/ChildComponentB";
import ChildComponentA from "@/app/Ch02Component/Exam01WrapElement/ChildComponentA";

export default function Page() {
    return (
        <>
            <section className="mx-6 mt-6 mb-12 overflow-hidden rounded-lg border border-gray-200 bg-white shadow-sm">
                <header className="border-b border-gray-200 bg-gray-50 px-5 py-3">
                    <h1 className="text-lg font-semibold">Exam01WrapElement</h1>
                </header>
                <div className="p-5">
                    <ChildComponentA/>
                    <ChildComponentB/>
                </div>
            </section>
        </>
    )
}
