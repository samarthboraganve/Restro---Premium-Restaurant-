import React, { useState, useEffect } from 'react'
import Animated from '../components/Animated.jsx'
import { dishes as fallbackDishes } from '../data/data.jsx'
import { fetchDishes } from '../services/api.js'
import { motion } from 'motion/react'

const Dishes = () => {
  const [dishList, setDishList] = useState(fallbackDishes)
  const [hoveredDish, setHoveredDish] = useState(null)

  useEffect(() => {
    let isMounted = true;
    fetchDishes().then((data) => {
      if (isMounted && data && data.length > 0) {
        setDishList(data);
      }
    });
    return () => { isMounted = false; };
  }, []);

  return (
    <section id='dishes' className='px-auto mt-44'>
        <div className="text-center mb-16">
            <Animated delay={0.2}>
                <p className="text-orange-500 font-medium uppercase mb-3.5">
                    Chef's Signature Selection
                </p>
            </Animated>
            <Animated>
                <h2 className="text-4xl md:text-5xl max-w-lg mx-auto text-balance text-orange-500">
                    Discover our signature dishes
                </h2>
            </Animated>
        </div>

        <div className="grid grid-cols-2 md:grid-cols-4 gap-12 md:gap-18 max-w-5xl mx-auto">
            {dishList.map((dish, index) => {
                const imageSrc = dish.imageUrl || dish.img;
                const formattedPrice = typeof dish.price === 'number' ? `$${dish.price}` : dish.price;
                return (
                    <Animated key={dish.id || index} y={80} delay={index * 0.1} className='flex flex-col items-center text-center shrink-0 cursor-pointer'>
                        <motion.div 
                            animate={{rotate: hoveredDish === index ? 360 : 0}}
                            transition={{type: 'spring', stiffness: 80, damping: 15}}
                            onMouseEnter={() => setHoveredDish(index)}
                            onMouseLeave={() => setHoveredDish(null)}
                        >
                            <img src={imageSrc} alt={dish.title} className="size-30 md:size-35 object-cover"/>
                        </motion.div>
                        <h3 className="mt-4 text-lg font-semibold text-gray-800">{dish.title}</h3>
                        <p className="text-orange-500 font-bold mt-2">{formattedPrice}</p>
                    </Animated>
                );
            })}
        </div>
    </section>
  )
}

export default Dishes