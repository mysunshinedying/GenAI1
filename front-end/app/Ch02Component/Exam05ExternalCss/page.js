import styles from "./Exam05ExternalCss.module.css";
import ChildComponent from "./ChildComponent";

export default function Exam05ExternalCss() {
  return (
    <section className={styles.card}>
      <header className={styles.header}>
        <h1 className={styles.title}>Exam05ExternalCss</h1>
      </header>
      <div className={styles.body}>
        <p className={styles.message}>This component is styled with an external CSS module.</p>
        <ChildComponent />
      </div>
    </section>
  );
}
