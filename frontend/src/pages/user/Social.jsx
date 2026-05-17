import '../../styles/Social.css'
import SocialTopic from '../../components/user/SocialTopic';
import SocialCategory from '../../components/user/SocialCategory';
function Social() {
    
    return (
        <div className="px-6 pb-6 !mx-0 w-full">
            <div className="social-container">
                <div className="social-topics glass-liquid">
                    <SocialTopic />
                </div>
                <div className="social-filter glass-liquid flex-1 pt-6">
                    <h3 className="social-filter-title">Danh mục</h3>
                    <div className='pl-10 flex flex-col gap-4'>
                        <SocialCategory id="toan-hoc" name="Toán học" />
                        <SocialCategory id="tin-hoc" name="Tin học" />
                        <SocialCategory id="hoa-hoc" name="Hóa Học" />
                        <SocialCategory id="sinh-hoc" name="Sinh học" />
                        <SocialCategory id="vat-ly" name="Vật lý" />
                        <SocialCategory id="anh-ngu" name="Anh ngữ" />
                        <SocialCategory id="su-hoc" name="Sử học" />
                        <SocialCategory id="van-hoc" name="Văn học" />
                        <SocialCategory id="xa-hoi-hoc" name="Xã hội học" />
                        <SocialCategory id="tam-ly-hoc" name="Tâm lý học" />
                    </div>
                </div>
            </div>
        </div>
    )
}

export default Social;