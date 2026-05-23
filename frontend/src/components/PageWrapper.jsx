import { motion } from "framer-motion";
import { useEffect, useState } from "react";
import { HashLoader } from "react-spinners";

function PageWrapper({ children }) {
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const timer = setTimeout(() => {
      setLoading(false);
    }, 400);

    return () => clearTimeout(timer);
  }, []);

  return (
    <>
      {loading && (
        <motion.div
          initial={{ opacity: 1 }}
          animate={{ opacity: 1 }}
          exit={{ opacity: 0 }}
          className="page-overlay"
        >
            <div className="glass-loader">
                <HashLoader className="loader" size={50} />
            </div>
        </motion.div>
      )}

      {!loading && (
        <motion.div>
          {children}
        </motion.div>
      )}
    </>
  );
}

export default PageWrapper;