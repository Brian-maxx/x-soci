import UserIcon from '../../assets/img/user.png'
import { CgMailReply } from "react-icons/cg";

const SocialTopics = [
    {
        topic: "Công nghệ" ,
        lastComment: "AI đang phát triển rất nhanh!" ,
        description: "Thảo luận về lập trình, AI, web, mobile..." 
    },
    {
        topic: "Thể thao",
        lastComment: "Trận hôm qua hay thật 🔥",
        description: "Bóng đá, gym, chạy bộ và các hoạt động thể chất"   
    },
    {
        topic: "Học tiếng Anh",
        lastComment: "Từ 'affection' nghĩa là gì?",
        description: "Chia sẻ kinh nghiệm học tiếng Anh, từ vựng, giao tiếp"                
    },
    {
        topic: "Tình yêu & cuộc sống",
        lastComment: "Làm sao để hiểu nhau hơn?",
        description: "Chuyện tình cảm, tâm sự, kinh nghiệm sống"  
    },
    {
        topic: "Game & giải trí",
        lastComment: "Game này chơi cuốn quá 😂",
        description: "Chơi game, phim ảnh, âm nhạc, thư giãn"          
    }
]
export default function SocialTopic({ avatar, topic, lastComment, description }) {
    return (
        <>
            {SocialTopics.map((element, index) => (
                <div className="social-topic-container" key={index}>
                    <div className="social-user-create">
                        <img src={avatar || UserIcon} alt="User Icon" className="social-user-icon w-[50px]" />
                    </div>
                    <div className="social-topic-content">
                        <h2 className="social-topic-title">{element.topic}</h2>
                        <p className="social-last-comment">
                            <CgMailReply className="w-[20px] h-[20px]" />
                            {element.lastComment}
                        </p>
                        <p className="social-topic-description">{element.description}</p>
                    </div>
                </div>
            ))}
        </>
    )
}