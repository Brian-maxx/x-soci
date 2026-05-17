export default function SocialCategory({ id, name }) {
    return (
        <div className="social-filter-option-container">
            <label class="checkbox-custom">
                <input type="checkbox" class="social-filter-checkbox" />
                <span class="checkmark"></span>
            </label>
            <input type="checkbox" id={id} name={name} className="social-filter-checkbox" />
            <p className="social-filter-option">{name}</p>
        </div>
    );
}