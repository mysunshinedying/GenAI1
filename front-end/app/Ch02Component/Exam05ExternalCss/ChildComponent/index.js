import styles from "./ChildComponent.module.css";

export default function ChildComponent() {
  return (
    <section className={styles.card}>
      <header className={styles.header}>
        <h2 className={styles.title}>ChildComponent</h2>
      </header>
      <div className={styles.body}>
        <p className={styles.message}>Folder-based UI component</p>
      </div>
    </section>
  );
}
