import ChildComponent from "./ChildComponent";
import Image from "next/image";
// import photo1 from "@/public/images/photo1.jpg";


export default function Exam01Props() {
  return (
      <section className="m-6 overflow-hidden rounded-lg border border-gray-200 bg-white shadow-sm">
        <header className="border-b border-gray-200 bg-gray-50 px-5 py-3">
          <h1 className="text-lg font-semibold">Exam01Props</h1>
        </header>
        <div className="space-y-4 p-5">
          <p className="text-sm text-slate-600">부모 컴포넌트가 props를 전달합니다.</p>
          <ChildComponent name="React" version={19}>
              <Image alt="이미지" src={'/images/photo1.jpg'} width={705} height={444} style={{width: '200px', height: 'auto'}}/>
          </ChildComponent>

            <ChildComponent/>
        </div>
      </section>
  );
}
