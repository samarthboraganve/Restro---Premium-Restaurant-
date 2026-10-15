import React, { useState, useEffect } from 'react'
import Animated from '../components/Animated'
import { Plus, X } from 'lucide-react'
import { faqs as fallbackFaqs } from '../data/data.jsx'
import { fetchFaqs } from '../services/api.js'

const FAQs = () => {
  const [faqList, setFaqList] = useState(fallbackFaqs)

  useEffect(() => {
    let isMounted = true;
    fetchFaqs().then((data) => {
      if (isMounted && data && data.length > 0) {
        setFaqList(data);
      }
    });
    return () => { isMounted = false; };
  }, []);

  return (
    <section id='faq' className='px-auto mt-44'>
      <div className="max-w-3xl mx-auto">
        <div className="text-center mb-14">
            <Animated delay={0.2}>
                <p className="text-orange-500 font-medium uppercase mb-4">
                    FAQs
                </p>
            </Animated>
            <Animated delay={0.2}>
                <h2 className="text-4xl md:text-5xl max-w-2xl mx-auto text-balance text-zinc-500">
                   Frequently Asked Questions
                </h2>
            </Animated>
        </div>

        <div className='space-y-3'>
            {faqList.map((faq, index) => (
                <Animated key={faq.id || index} y={150} delay={index * 0.15}>
                    <details className="border border-slate-200 text-zinc-500 rounded-lg group">
                        <summary className='flex items-center justify-between p-4 cursor-pointer list-none hover:bg-slate-50/50 transition-colors [&::-webkit-details-marker]:hidden'>
                            <span className='text-zinc-700 pr-4 font-medium'>
                                {faq.question}
                            </span>
                            <span className='size-7 rounded-full bg-black/5 grid place-content-center shrink-0'>
                                <Plus size={14} className="group-open:hidden"/>
                                <X size={14} className="hidden group-open:block"/>
                            </span>
                        </summary>
                        <p className='px-5 pb-4 leading-relaxed text-zinc-600'>
                            {faq.answer}
                        </p>
                    </details>
                </Animated>
            ))}
        </div>
      </div>
    </section>
  )
}

export default FAQs
