import RepeatTableRow from "./RepeatTableRow";
import RepeatWithIndex from "@/app/Ch02Component/Exam08Repeat/RepeatWithIndex";

export default function Exam08Repeat() {

    return (
        <section className="m-6 overflow-hidden rounded-lg border border-gray-200 bg-white shadow-sm">
            <header className="border-b border-gray-200 bg-gray-50 px-5 py-3">
                <h1 className="text-lg font-semibold">Exam08Repeat</h1>
            </header>
            <div className="p-5">
                <RepeatTableRow/>
                <hr className="border-b border-gray-200 m-4"/>
                <RepeatWithIndex/>
            </div>
        </section>
    );
}
